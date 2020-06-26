#!/bin/bash

# all projects from local conda package
# usage:
# build all conda packages locally
# ./install_beakerx_all_conda_package_notebook.sh beakerx_conda_env_name
#  conda activate beakerx_conda_env_name

conda create -y -n $1 python=3.7 pip
source ~/anaconda3/etc/profile.d/conda.sh
conda activate $1

conda install -y --use-local beakerx_all

echo To activate this environment, use:
echo
echo      conda activate $1
echo      
