#!/bin/bash
(conda env create -n $1 -f configuration.yml)
source ~/anaconda3/etc/profile.d/conda.sh
conda activate $1
(cd ../../beakerx_base; pip install -r requirements.txt --verbose)
(cd ../../beakerx_kernel_base; ./gradlew clean install)
(cd ../../beakerx_tabledisplay/beakerx_tabledisplay; pip install -r requirements.txt --verbose; beakerx_tabledisplay install)
(cd ../../beakerx_kernel_autotranslation; pip install -r requirements.txt --verbose; beakerx_kernel_autotranslation install)
(cd ../../beakerx_widgets/beakerx; pip install -e . --verbose; beakerx install)

############################ choose which kernel to install ###############################################
(cd ../../beakerx_kernel_groovy/groovy-dist; pip install -e . --verbose; beakerx_kernel_groovy install)
###########################################################################################################

echo To activate this environment, use:
echo      
echo      conda activate $1
echo      
