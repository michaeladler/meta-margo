#!/usr/bin/env bash
set -euo pipefail
# SPDX-FileCopyrightText: 2025 Margo
#
# SPDX-License-Identifier: MIT
#
# SPDX-FileContributor: Michael Adler <michael.adler@siemens.com>

MACHINE=${1:-}
if [[ "$MACHINE" = "" ]]; then
    echo "Usage: $0 [qemu-amd64|qemu-arm64]"
    exit 1
fi

case $MACHINE in
qemu-amd64)
    IMAGE_DIR=${KAS_WORK_DIR:-.}/build/tmp/deploy/images/qemu-amd64
    KERNEL_FILE=$IMAGE_DIR/margo-device-image-cip-core-bookworm-qemu-amd64-vmlinuz
    INITRD_FILE=$IMAGE_DIR/margo-device-image-cip-core-bookworm-qemu-amd64-initrd.img
    IMAGE_FILE=$IMAGE_DIR/margo-device-image-cip-core-bookworm-qemu-amd64.ext4
    KERNEL_CMDLINE="console=ttyS0,115200 root=/dev/sda rw"

    exec qemu-system-x86_64 -M pc -enable-kvm -smp 4 -m 2G \
        -nographic -serial mon:stdio \
        -kernel "$KERNEL_FILE" \
        -append "$KERNEL_CMDLINE" \
        -initrd "$INITRD_FILE" \
        -device ide-hd,drive=disk \
        -drive "file=$IMAGE_FILE,discard=unmap,if=none,id=disk,format=raw" \
        -device virtio-net-pci,netdev=net \
        -netdev user,id=net,hostfwd=tcp:127.0.0.1:22222-:22
    ;;

qemu-arm64)
    IMAGE_DIR=${KAS_WORK_DIR:-.}/build/tmp/deploy/images/qemu-arm64
    KERNEL_FILE=$IMAGE_DIR/margo-device-image-cip-core-bookworm-qemu-arm64-vmlinux
    INITRD_FILE=$IMAGE_DIR/margo-device-image-cip-core-bookworm-qemu-arm64-initrd.img
    IMAGE_FILE=$IMAGE_DIR/margo-device-image-cip-core-bookworm-qemu-arm64.ext4
    KERNEL_CMDLINE="console=ttyAMA0,115200 root=/dev/vda rw"

    exec qemu-system-aarch64 -M virt -cpu cortex-a53 -smp 4 -m 2G \
        -nographic -serial mon:stdio \
        -kernel "$KERNEL_FILE" \
        -append "$KERNEL_CMDLINE" \
        -initrd "$INITRD_FILE" \
        -drive "file=$IMAGE_FILE,if=none,id=root_disk,index=0,media=disk,format=raw" \
        -device virtio-blk-pci,drive=root_disk \
        -device virtio-net-pci,netdev=net \
        -netdev user,id=net,hostfwd=tcp:127.0.0.1:22222-:22
    ;;
*)
    echo "Unsupported machine: $MACHINE"
    exit 1
    ;;
esac
