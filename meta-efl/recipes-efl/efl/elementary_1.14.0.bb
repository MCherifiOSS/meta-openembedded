require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

# only for target, because configure doesn't default to sane default when
# these paths aren't passed in -native build
SRC_URI_append_class-target = " file://0001-Makefile-Use-elementary_codegen-defined-in-configure.patch"

SRC_URI[md5sum] = "e77075b8a80e9a931b91388d608c211c"
SRC_URI[sha256sum] = "aa06ca1b332b3cd29ffd136c1f10edf08a06906b3532785ce7e390c1cad6090e"

# autotools-brokensep - configure updates Elementary.h correctly in ${B}, but then build is using Elementary.h from ${S}
# which includes #define ELM_EMAP (instead of #undef ELM_EMAP) and building fails
B = "${S}"
