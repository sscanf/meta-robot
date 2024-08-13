SUMMARY = "PNG image format decoding library"
DESCRIPTION = "An open source project to develop and maintain the reference \
library for use in applications that read, create, and manipulate PNG \
(Portable Network Graphics) raster image files. "
HOMEPAGE = "http://www.libpng.org/"
SECTION = "libs"
LICENSE = "Libpng"
DEPENDS = "zlib"

LIBV = "16"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=ebf58d893aeb1315372c831a3b7813b7"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=5c900cc124ba35a274073b5de7639b13"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f533bc367bfd43f556b6f782234c076"

SRC_URI =  "git://github.com/glennrp/libpng.git;branch=master;protocol=https \
            file://0001-solved-png-width-large-chunk-metadata.diff \
"
BINCONFIG = "${bindir}/libpng-config ${bindir}/libpng16-config"

inherit autotools binconfig-disabled pkgconfig

SRC_URI[sha256sum] = "55311f7344499eab1531e9abb19d86761356e52f678cd49599d85291b7833e37"

# Work around missing symbols
EXTRA_OECONF:append_class-target = " ${@bb.utils.contains("TUNE_FEATURES", "neon", "--enable-arm-neon=on", "--enable-arm-neon=off" ,d)}"

PACKAGES =+ "${PN}-tools"

FILES:${PN}-tools = "${bindir}/png-fix-itxt ${bindir}/pngfix ${bindir}/pngcp"

BBCLASSEXTEND = "native nativesdk"

# CVE-2019-17371 is actually a memory leak in gif2png 2.x
#CVE_CHECK_WHITELIST += "CVE-2019-17371"
CVE_CHECK_IGNORE += "CVE-2019-17371"

