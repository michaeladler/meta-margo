# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

inherit dpkg

_REV="2ebc4fff4d1bab5a08f07e90e09ca88f6e28deb0"

SRC_URI += "\
    https://github.com/Silvanoc/margo-gitops-poc/archive/${_REV}.tar.gz;downloadfilename=app.tar.gz;unpack=0 \
    file://Makefile \
"
SRC_URI[sha256sum] = "7323450450fd6079624fa47715e1c1cbf1f48803466b8dd62d2d8307cb75be27"

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
