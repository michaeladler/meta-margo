# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

inherit dpkg

_REV="0b0606f70e91533b53e9d0e5bcaa994598ba7078"

SRC_URI += "\
    https://github.com/Silvanoc/margo-gitops-poc/archive/${_REV}.tar.gz;downloadfilename=app.tar.gz;unpack=0 \
    file://Makefile \
"
SRC_URI[sha256sum] = "7b161ad61f814b00d1b32a3c002ddc8f1d8278dd55731cd8f3afbe308900e970"

DESCRIPTION = "oci-watcher"

MAINTAINER = "Michael Adler <michael.adler@siemens.com>"

DEBIAN_BUILD_DEPENDS = "golang:native"
DEBIAN_DEPENDS = "\${shlibs:Depends}"

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
