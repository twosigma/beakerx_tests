#!/bin/bash

# all projects in one workspace
# in the meta.yaml replace YOUR_LOCAL_PATH for all components
# usage:
# rm -rf ~/anaconda3/conda-bld/*
# ./build_conda_packages.sh

set -e

rm -rf /usr/local/anaconda3/conda-bld

(cd ../../beakerx_kernel_groovy/groovy-dist; rm -rf build dist beakerx_kernel_groovy.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx_kernel_java/java-dist; rm -rf build dist beakerx_kernel_java.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx_kernel_scala/scala-dist; rm -rf build dist beakerx_kernel_scala.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx_kernel_kotlin/kotlin-dist; rm -rf build dist beakerx_kernel_kotlin.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx_kernel_sql/sql-dist; rm -rf build dist beakerx_kernel_sql.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx_kernel_clojure/clojure-dist; rm -rf build dist beakerx_kernel_clojure.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx_kernel_autotranslation; rm -rf build dist beakerx_kernel_autotranslation.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx_base; rm -rf build dist beakerx_base.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx_tabledisplay/beakerx_tabledisplay; rm -rf build dist beakerx_tabledisplay.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx_widgets/beakerx_widgets; rm -rf build dist beakerx_widgets.egg-info; python setup.py sdist; conda-build ./conda_recipe)
(cd ../../beakerx/beakerx-dist; rm -rf build dist beakerx_all.egg-info; python setup.py sdist; conda-build ./conda_recipe)
