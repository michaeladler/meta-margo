# meta-margo

This repository generates bootable images of [Margo](https://margo.org/) appliances using [Isar](https://github.com/ilbers/isar) and [isar-cip-core](https://gitlab.com/cip-project/cip-core/isar-cip-core).

- **Isar**: A set of scripts for building software packages and repeatable generation of Debian-based root filesystems with customizations.
- **isar-cip-core**: An ISAR layer that provides recipes for essential components such as an SLTS Linux kernel, secure boot, over-the-air (OTA) firmware updates, and more.

## Prerequisites

- [Docker](https://www.docker.com/) or [Podman](https://podman.io/)
- Optional: [just](https://github.com/casey/just) (to run the tasks in [justfile](justfile))
- Optional: [QEMU](https://www.qemu.org/) (to boot the generated root filesystems)

## Build and Run a Margo Device

### Build

To generate a bootable ext4 root filesystem for the default amd64 architecture, run:

```sh
$ just build-margo-device
```

To build for arm64 architecture, run:

```sh
$ just build-margo-device qemu-arm64
```

### Run

To launch the resulting image (ext4) in QEMU, use the following commands:

```sh
# For qemu-amd64
$ just start-margo-device

# For qemu-arm64
$ just start-margo-device qemu-arm64
```
