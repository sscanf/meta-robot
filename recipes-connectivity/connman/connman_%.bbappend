FILESEXTRAPATHS:append:= "${THISDIR}/${PN}:"

SRC_URI += "file://0001-add-uap0-main-configuration.patch \
            file://0002-connman-clock-ntp-client-should-not-update-time-time.patch \
            file://settings \
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
