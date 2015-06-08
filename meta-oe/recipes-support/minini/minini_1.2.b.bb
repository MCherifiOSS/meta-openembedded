SUMMARY = "A minimal INI file parser"
DESCRIPTION = "minIni is a programmer's library to read and write INI files in \
embedded systems. minIni takes little resources, has a deterministic memory \
footprint and can be configured for various kinds of file I/O libraries. minIni \
provides functionality for reading, writing and deleting keys from an INI file, \
all in 830 lines of (commented) source code (version 1.2) in C (the code also \
compiles in C++ and comes with a wrapper class)."
HOMEPAGE = "https://code.google.com/p/minini/"

# License is Apache 2.0 with an exception to allow object code built from
# unmodified original sources to be distributed more freely. See LICENSE
# file for details.

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb21481ad45c5578ae8c8d37b8c8d76d"

SRC_URI = "http://minini.googlecode.com/files/minIni_12b.zip;subdir=${BP}"

SRC_URI[md5sum] = "32740621098e3f0a321c7c23f4bcbc5d"
SRC_URI[sha256sum] = "b08839af74acb36061fb76e1123bf56711bc5cf7a08b32e189b0ad78a2e888e2"

do_configure[noexec] = "1"

do_compile() {
    ${CC} minIni.c -shared -fPIC -o libminini.so.0.0 -Wl,-soname,libminini.so.0 ${CFLAGS} ${LDFLAGS}
}

do_install () {

    install -d ${D}${libdir}
    install -m 0644 libminini.so.0.0 ${D}${libdir}/
    ln -s libminini.so.0.0 ${D}${libdir}/libminini.so
    ln -s libminini.so.0.0 ${D}${libdir}/libminini.so.0

    install -d ${D}${includedir}/minini
    install -m 0644 minIni.h ${D}${includedir}/minini/
    install -m 0644 minGlue-stdio.h ${D}${includedir}/minini/minGlue.h
}
