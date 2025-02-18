[![ci](https://github.com/michaeladler/meta-margo/actions/workflows/ci.yml/badge.svg)](https://github.com/michaeladler/meta-margo/actions/workflows/ci.yml)

# meta-margo

This repository is used to generate bootable images of [Margo](https://margo.org/) appliances using [Isar](https://github.com/ilbers/isar) and [isar-cip-core](https://gitlab.com/cip-project/cip-core/isar-cip-core).

- **Isar**: A collection of scripts designed for building software packages and generating Debian-based root filesystems with customizations in a repeatable manner.
- **isar-cip-core**: An ISAR layer that provides recipes for essential components, including an SLTS Linux kernel, secure boot, over-the-air (OTA) firmware updates, and more.

## Prerequisites

- [Docker](https://www.docker.com/) or [Podman](https://podman.io/) (you must have the ability to start privileged containers)
- Optional: [QEMU](https://www.qemu.org/) (for booting the generated root filesystems)

## Building Margo Images

Open the image configuration menu and select the desired target and its options:

```sh
$ ./kas-container menu
```

## Booting Margo Images

Note: Prebuilt images can be downloaded from GitHub Actions. Extract them into subdirectories within `build/tmp/deploy/images`.

Execute the following command and select the desired board:

```sh
$ ./scripts/start-margo-device.sh [qemu-amd64|qemu-arm64]
```

You can also log in via SSH, for example:

```sh
$ ssh root@localhost -p 22222
```
