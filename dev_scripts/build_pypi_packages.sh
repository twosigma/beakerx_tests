#!/bin/bash

# all projects in one workspace

set -e

(cd ../../beakerx_kernel_groovy/groovy-dist; rm -rf build dist beakerx_kernel_groovy.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx_kernel_java/java-dist; rm -rf build dist beakerx_kernel_java.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx_kernel_scala/scala-dist; rm -rf build dist beakerx_kernel_scala.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx_kernel_kotlin/kotlin-dist; rm -rf build dist beakerx_kernel_kotlin.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx_kernel_sql/sql-dist; rm -rf build dist beakerx_kernel_sql.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx_kernel_clojure/clojure-dist; rm -rf build dist beakerx_kernel_clojure.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx_kernel_autotranslation; rm -rf build dist beakerx_kernel_autotranslation.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx_base; rm -rf build dist beakerx_base.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx_tabledisplay/beakerx_tabledisplay; rm -rf build dist beakerx_tabledisplay.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx_widgets/beakerx_widgets; rm -rf build dist beakerx_widgets.egg-info; python3 setup.py sdist bdist_wheel)
(cd ../../beakerx/beakerx-dist; rm -rf build dist beakerx_all.egg-info; python3 setup.py sdist bdist_wheel)
