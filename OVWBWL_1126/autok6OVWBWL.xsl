<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" />
    <xsl:key name="varosok" match="auto" use="tulaj/varos" />
    <xsl:template match="/">
        <varosok_osszesites>
            <xsl:for-each select="autok/auto[generate-id() = generate-id(key('varosok', tulaj/varos)[1])]">
                <varos>
                    <nev><xsl:value-of select="tulaj/varos" /></nev>
                    <darabszam><xsl:value-of select="count(key('varosok', tulaj/varos))" /></darabszam>
                    <osszar><xsl:value-of select="sum(key('varosok', tulaj/varos)/ar)" /></osszar>
                </varos>
            </xsl:for-each>
        </varosok_osszesites>
    </xsl:template>
</xsl:stylesheet>