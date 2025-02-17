# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

inherit dpkg

_REV="c02756cdff615b5caeaadbb8c57acf3accab8ad3"

SRC_URI += "\
    https://github.com/Silvanoc/margo-gitops-poc/archive/${_REV}.tar.gz;downloadfilename=app.tar.gz;unpack=0 \
    file://Makefile \
"
SRC_URI[sha256sum] = "0be8ace642f28c6cca41188db22f876ca09bd880fc67d2aa0248705996b1a0a4"

DESCRIPTION = "oci-watcher"

MAINTAINER = "Michael Adler <michael.adler@siemens.com>"

DEBIAN_BUILD_DEPENDS = "golang-1.23"
DEBIAN_DEPENDS = "\${shlibs:Depends}"

ISAR_CROSS_COMPILE = "0"

S = "${WORKDIR}/git"

do_prepare_build[cleandirs] += "${S}/debian"
do_prepare_build() {
    # This step creates everything sbuild needs. For further details
    # you might want to look at its implementation.
    deb_debianize

    cd ${S}
    tar -xzf ${WORKDIR}/app.tar.gz --strip-components=2 margo-gitops-poc-${_REV}/oci-watcher
    cp ${WORKDIR}/Makefile ${S}/
}

do_dpkg_buildpackage[network] = "1"
