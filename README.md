[![ci](https://github.com/michaeladler/meta-margo/actions/workflows/ci.yml/badge.svg)](https://github.com/michaeladler/meta-margo/actions/workflows/ci.yml)

# meta-margo

This repository generates bootable images of [Margo](https://margo.org/) appliances using [Isar](https://github.com/ilbers/isar) and [isar-cip-core](https://gitlab.com/cip-project/cip-core/isar-cip-core).

- **Isar**: A set of scripts for building software packages and repeatable generation of Debian-based root filesystems with customizations.
- **isar-cip-core**: An ISAR layer that provides recipes for essential components such as an SLTS Linux kernel, secure boot, over-the-air (OTA) firmware updates, and more.

## Prerequisites

- [Docker](https://www.docker.com/) or [Podman](https://podman.io/) (you must be able to start privileged containers)
- Optional: [QEMU](https://www.qemu.org/) (to boot the generated root filesystems)

## Building Margo Images

Open up the image configuration menu and select the desired target and its options:

```
$ ./kas-container menu
```

## Boot Margo Images

Run the following command and select the desired board:

```
$ ./scripts/start-margo-device.sh [qemu-amd64|qemu-arm64]
```

You can also login via SSH, e.g.

```
$ ssh root@localhost -p 22222
```
