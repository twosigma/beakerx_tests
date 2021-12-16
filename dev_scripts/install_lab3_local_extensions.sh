#!/bin/bash

set -e

if [ ! -z "$1" ]
then
	(conda env create -n $1 -f configuration-lab3.yml)
	conda activate $1
fi

(cd ../../beakerx_base; pip install -r requirements.txt --verbose)
(cd ../../beakerx_tabledisplay/beakerx_tabledisplay/js; yarn build:labextension)
(cd ../../beakerx_tabledisplay/beakerx_tabledisplay; pip install -r requirements.txt --verbose; beakerx_tabledisplay install)
(cd ../../beakerx_widgets/beakerx_widgets/js; yarn build:labextension)
(cd ../../beakerx_widgets/beakerx_widgets; pip install -r requirements.txt --verbose; beakerx install)
jupyter labextension install @jupyter-widgets/jupyterlab-manager

if [ ! -z "$1" ]
then
	echo To activate this environment, use:
	echo
	echo      conda activate $1
	echo  
fi    
