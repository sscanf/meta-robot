require recipes-images/images/robot-minimal-image.bb
#require recipes-images/images/tdx-reference-minimal-image.bb

SUMMARY = "Robot image"
DESCRIPTION = "Robot image for Toradex imx8"

inherit populate_sdk_qt5

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "robot-image"

TOOLCHAIN_TARGET_TASK:append = " kernel-devsrc"

IMAGE_CLASSES += "image_type_tezi"
IMAGE_FSTYPES = "tar.xz teziimg"

SYSTEMD_DEFAULT_TARGET = "graphical.target"

APP_LAUNCH_WAYLAND ?= "xterm"
APP_LAUNCH_X11 ?= "xterm"

IMAGE_FEATURES += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11', \
                                                       '', d), d)} \
"
CONMANPKGS ?= "connman connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-client"


GSTREAMER_1_0_GROUP = "      \
    gstreamer1.0             \
    gstreamer1.0-meta-base   \
    gstreamer1.0-plugins-ugly\
    gstreamer1.0-plugins-good\
    gstreamer1.0-plugins-base\
    gstreamer1.0-plugins-bad \
    gstreamer1.0-libav       \
    libgstcodecparsers-1.0   \
"

#sip3-dev
#
IMAGE_INSTALL += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', \
                         'weston weston-init weston-examples ${APP_LAUNCH_WAYLAND}', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', \
                         'weston-xwayland xterm', \
       bb.utils.contains('DISTRO_FEATURES', 'x11', '${APP_LAUNCH_X11}', '', d), d)} \
    \
    ${CONMANPKGS} \
    ${GSTREAMER_1_0_GROUP} \
    python3-pyqt5 \
    python3-pip \
    connman \
    busybox \
    less \
    makedevs \
    mime-support \
    util-linux \
    v4l-utils \
    opencv \
    wpa-supplicant \
    libsoc    \
    hostapd \
    wireless-regdb-static \
    packagegroup-basic  \
    tensorflow-lite \
"

IMAGE_INSTALL += "${@bb.utils.contains("MACHINE", "qemux86-64", "","${TORADEX_GROUP}",d)}"
