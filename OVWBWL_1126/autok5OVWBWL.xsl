<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" />
    <xsl:key name="tipusok" match="auto" use="tipus" />
    <xsl:template match="/">
        <tipusok_sorrend>
            <xsl:for-each select="autok/auto[generate-id() = generate-id(key('tipusok', tipus)[1])]">
                <tipus>
                    <nev><xsl:value-of select="tipus" /></nev>
                    <darabszam><xsl:value-of select="count(key('tipusok', tipus))" /></darabszam>
                </tipus>
            </xsl:for-each>
        </tipusok_sorrend>
    </xsl:template>
</xsl:stylesheet>