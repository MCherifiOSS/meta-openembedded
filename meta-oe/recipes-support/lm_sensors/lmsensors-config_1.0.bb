SUMMARY = "lm_sensors configuration files"
DESCRIPTION = "Hardware health monitoring configuration files"
HOMEPAGE = "http://www.lm-sensors.org/"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://fancontrol \
           file://sensord.cgi \
           file://sensord.conf \
           file://sensors.conf \
"
S = "${WORKDIR}"

RDEPENDS_${PN}-dev = ""

do_install() {
    # Install fancontrol configuration file
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/fancontrol ${D}${sysconfdir}

    # Install libsensors configuration file
    install -d ${D}${sysconfdir}/sensors.d
    install -m 0644 ${WORKDIR}/sensors.conf ${D}${sysconfdir}/sensors.d

    # Install sensord configuration file
    install -m 0644 ${WORKDIR}/sensord.conf ${D}${sysconfdir}

    # Install sensord.cgi script and create world-writable
    # web-accessible sensord directory
    install -d ${D}/www/pages/cgi-bin
    install -m 0755 ${WORKDIR}/sensord.cgi ${D}/www/pages/cgi-bin
    install -d -m a=rwxs ${D}/www/pages/sensord
}

# libsensors configuration
PACKAGES =+ "${PN}-libsensors"

# sensord logging daemon configuration
PACKAGES =+ "${PN}-sensord"

# fancontrol script configuration
PACKAGES =+ "${PN}-fancontrol"

# sensord web cgi support
PACKAGES =+ "${PN}-cgi"
RRECOMMENDS_${PN}-cgi = "lighttpd lighttpd-module-cgi"
RDEPENDS_${PN}-cgi = "${PN}-sensord rrdtool"
FILES_${PN}-cgi = "/www/*" 

# libsensors configuration file
FILES_${PN}-libsensors = "${sysconfdir}/sensors.d/sensors.conf"

# sensord logging daemon configuration files
FILES_${PN}-sensord = "${sysconfdir}/sensord.conf"

# fancontrol script configuration file
FILES_${PN}-fancontrol = "${sysconfdir}/fancontrol"
