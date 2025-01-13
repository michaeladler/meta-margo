# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

.PHONY: build
build:
	export GOTOOLCHAIN=local; /usr/lib/go-1.23/bin/go build -ldflags="-s -w"

.PHONY: install
install:
	install -D -m0644 oci-watcher $(DESTDIR)$(PREFIX)/usr/bin/oci-watcher
