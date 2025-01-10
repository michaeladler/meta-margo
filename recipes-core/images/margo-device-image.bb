# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

inherit image

DESCRIPTION = "Margo Device image"

IMAGE_PREINSTALL += " \
    docker.io \
    docker-compose \
    iproute2 \
    vim \
    less \
    curl \
"

IMAGE_INSTALL += " \
    margo-customizations \
    insecure-ssh \
"
