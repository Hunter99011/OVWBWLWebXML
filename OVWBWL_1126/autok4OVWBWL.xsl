<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" />
    <xsl:template match="/">
        <miskolci_rendszamok>
            <xsl:for-each select="autok/auto[tulaj/varos='Miskolc']">
                <rendszam>
                    <xsl:value-of select="@rsz" />
                </rendszam>
            </xsl:for-each>
        </miskolci_rendszamok>
    </xsl:template>
</xsl:stylesheet>