# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

inherit dpkg-raw

SRC_URI += "\
    file://ssh-permit-root.conf \
"

DEPENDS = "sshd-regen-keys"
DEBIAN_DEPENDS = "sshd-regen-keys, rsync"

do_install() {
    install -D -m 0644 ${WORKDIR}/ssh-permit-root.conf ${D}/etc/ssh/sshd_config.d/ssh-permit-root.conf
}
