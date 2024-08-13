FILESEXTRAPATHS:append := "${THISDIR}/${PN}:"

SRC_URI += "file://hostapd.conf \
            file://hostapd.network \
"

SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
	install -d ${D}${sysconfdir}
	install -d ${D}${base_libdir}/systemd/network
	install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}/hostapd.conf
	install -m 0644 ${WORKDIR}/hostapd.network ${D}${base_libdir}/systemd/network/hostapd.network
}

FILES:${PN} += "/etc/hostapd.conf \
                /lib/systemd/network/hostapd.network" 
