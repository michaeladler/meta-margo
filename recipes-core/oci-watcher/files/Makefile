# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

ifneq ($(DEB_BUILD_GNU_TYPE),$(DEB_HOST_GNU_TYPE))

ifeq ($(DEB_HOST_GNU_TYPE),aarch64-linux-gnu)
GOARCH = arm64
endif

ifeq ($(DEB_HOST_GNU_TYPE),x86_64-linux-gnu)
GOARCH = amd64
endif

endif

.PHONY: build
build:
	export GOTOOLCHAIN=local GOARCH=$(GOARCH); go build -ldflags="-s -w"

.PHONY: install
install:
	install -D -m0644 oci-watcher $(DESTDIR)$(PREFIX)/usr/bin/oci-watcher
