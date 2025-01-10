# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

default:
    just --list

build-margo-device MACHINE="qemu-amd64":
    #!/bin/sh
    export KAS_MACHINE={{ MACHINE }}
    ./kas-container --isar build kas-margo-device.yml

start-margo-device MACHINE="qemu-amd64":
    ./scripts/start-margo-device.sh "{{ MACHINE }}"

ssh:
    ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null root@localhost -p 22222
