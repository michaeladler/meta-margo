# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

inherit dpkg-raw

SRC_URI += "\
    file://01-ethernet.network \
    file://postinst \
"

DESCRIPTION = "margo image customizations"

DEBIAN_DEPENDS = "systemd-resolved"

do_install[cleandirs] += "${D}/etc/systemd/network"
do_install() {
    install -v -m 644 ${WORKDIR}/01-ethernet.network ${D}/etc/systemd/network/01-ethernet.network
}
