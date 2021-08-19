#!/usr/bin/env bash
set -euo pipefail

# make sure you pulled the starter-boot repo and built that based on latest on main branch.
# RESOURCE_VAR_NAME=facilityVisit SERVICE_NAME=FacilityVisit PROJECT_NAME=facility-visit

echo "Project ....... $PROJECT_NAME"
echo "Service ....... $SERVICE_NAME"
echo "Resource var .. $RESOURCE_VAR_NAME"

export CC_PROJECT=${1:-git+ssh://git@github.com/department-of-veterans-affairs/lighthouse-di-starterkit-java}

if [ -d $PROJECT_NAME ]; then mv -v $PROJECT_NAME $PROJECT_NAME.${date +%s}; fi

cookiecutter "${CC_PROJECT}" \
  --no-input \
  --verbose \
  --debug-file /tmp/cc1.out \
  --directory templates/project \
  PROJECT_NAME=$PROJECT_NAME \
  SERVICE_NAME=$SERVICE_NAME \
  RESOURCE_VAR_NAME=$RESOURCE_VAR_NAME

cookiecutter "${CC_PROJECT}" \
  --no-input \
  --verbose \
  --directory templates/resource \
  --debug-file /tmp/cc2.out \
  -f \
  PROJECT_NAME=$PROJECT_NAME \
  SERVICE_NAME=$SERVICE_NAME \
  RESOURCE_VAR_NAME=$RESOURCE_VAR_NAME

(cd $PROJECT_NAME && git init && git add . && git commit -m "initial version generated from starterkit")
