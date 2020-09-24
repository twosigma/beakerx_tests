import os
import sys
import argparse
import signal
import subprocess
import platform
import psutil
from subprocess import check_output, CalledProcessError

here = os.path.abspath(os.path.dirname(__file__))
beakerx_dir = os.path.abspath(os.path.join(here, ".."))
test_dir = here
cur_app = 'notebook'
conda_env = 'beakerx'
tst_templ = 'groovy.*'
tbl_ver= 'v0'
create_exp_data='no'

argumentList = sys.argv[1:]
parser = argparse.ArgumentParser()
parser.add_argument("--app", help="define target application 'notebook' or 'lab'")
parser.add_argument("--env", help="define target conda environment where is jupyter notebook or lab")
parser.add_argument("--tst", help="define tests we need to run; for example: groovy.* ")
parser.add_argument("--tbl", help="define version table tests data")
parser.add_argument("--exp", help="create expected data (yes/no)")
args = parser.parse_args()

if args.app:
    cur_app = args.app

if args.env:
    conda_env = args.env

if args.tst:
    tst_templ = args.tst

if args.tbl:
    tbl_ver = args.tbl

if args.exp:
    create_exp_data = args.exp

# start jupyter notebook
if platform.system() == 'Windows':
    nb_command = 'jupyter %(app)s --no-browser --notebook-dir="%(dir)s" --NotebookApp.token=""' % { "app" : cur_app, "dir" : beakerx_dir }
    print(nb_command)
    beakerx = subprocess.Popen(nb_command, stderr=subprocess.STDOUT, stdout=subprocess.PIPE, shell=True)
else:
    nb_command = 'jupyter %(app)s --no-browser --notebook-dir="%(dir)s" --NotebookApp.token=""' % { "app" : cur_app, "dir" : beakerx_dir }
    print(nb_command)
    beakerx = subprocess.Popen(nb_command, shell=True, executable="/bin/bash", preexec_fn=os.setsid, stderr=subprocess.STDOUT, stdout=subprocess.PIPE)
# wait for notebook server to start up
while 1:
    line = beakerx.stdout.readline().decode('utf-8').strip()
    if not line:
        continue
    print(line)
    if 'Use Control-C to stop this server' in line:
        break

# run tests
tst_command = 'gradle --no-daemon cleanTest test -Dcur_app=%(app)s -Dtbl_ver=%(tbl)s -Dcreate_exp_data=%(exp)s --tests "com.twosigma.beakerx.autotests.%(tst)s" --info' % { "app" : cur_app, "tbl" : tbl_ver, "exp" : create_exp_data, "tst" : tst_templ }
print(tst_command)
result = subprocess.call(tst_command, shell=True)

# kill unused processes
if platform.system() == 'Windows':
    for proc in psutil.process_iter():
        if proc.name() in ["jupyter-lab.exe", "jupyter.exe", "jupyter-notebook.exe", "chromedriver.exe"]:
            print(proc)
            os.kill(proc.pid, signal.SIGTERM)
if result:
    sys.exit(20)
