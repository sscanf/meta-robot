FILESEXTRAPATHS:append:= "${THISDIR}/${PN}:"

SRC_URI += " file://settings \
"

do_install:append() {
    install -d ${D}${sysconfdir}/connman/
    install -d ${D}${localstatedir}/lib/connman/
    install -m 0644 ${S}/src/main.conf ${D}${sysconfdir}/connman/
    install -m 0644 ${WORKDIR}/settings ${D}${localstatedir}/lib/connman/
}

FILES:${PN} += "/etc/connman/ \
                /var/lib/connman \
"
