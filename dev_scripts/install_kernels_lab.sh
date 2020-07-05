#!/bin/bash

# all projects in one workspace
# usage:
# ./install_kernels_lab.sh beakerx_conda_env_name
#  conda activate beakerx_conda_env_name

(conda env create -n $1 -f configuration.yml)
source ~/anaconda3/etc/profile.d/conda.sh
conda activate $1
(conda install -y -c conda-forge jupyterlab=1)

(cd ../../beakerx_kernel_base; ./gradlew clean install)
(cd ../../beakerx_kernel_groovy/groovy-dist; pip install -r requirements.txt --verbose; beakerx_kernel_groovy install)
(cd ../../beakerx_kernel_java/java-dist; pip install -r requirements.txt --verbose; beakerx_kernel_java install)
(cd ../../beakerx_kernel_scala/scala-dist; pip install -r requirements.txt --verbose; beakerx_kernel_scala install)
(cd ../../beakerx_kernel_kotlin/kotlin-dist; pip install -r requirements.txt --verbose; beakerx_kernel_kotlin install)
(cd ../../beakerx_kernel_sql/sql-dist; pip install -r requirements.txt --verbose; beakerx_kernel_sql install)
(cd ../../beakerx_kernel_clojure/clojure-dist; pip install -r requirements.txt --verbose; beakerx_kernel_clojure install)
(cd ../../beakerx_kernel_autotranslation; pip install -r requirements.txt --verbose; beakerx_kernel_autotranslation install)
(cd ../../beakerx_base; pip install -r requirements.txt --verbose)
(cd ../../beakerx_tabledisplay/beakerx_tabledisplay; pip install -r requirements.txt --verbose; beakerx_tabledisplay install)
#(cd ../../beakerx_widgets/beakerx; pip install -r requirements.txt --verbose; beakerx install)

jupyter labextension install @jupyter-widgets/jupyterlab-manager --no-build
#(cd ../../beakerx_widgets/js/lab; jupyter labextension install . --no-build)
#(cd ../../beakerx_widgets/js/lab-theme-dark; jupyter labextension install . --no-build)
#(cd ../../beakerx_widgets/js/lab-theme-light; jupyter labextension install . --no-build)
jupyter lab build

echo To activate this environment, use:
echo
echo      conda activate $1
echo      
