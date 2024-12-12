<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" />
    <xsl:template match="/">
        <autok_sorrend_xml>
            <xsl:for-each select="autok/auto">
                <xsl:sort select="ar" data-type="number" order="ascending" />
                <auto>
                    <rendszam><xsl:value-of select="@rsz" /></rendszam>
                    <ar><xsl:value-of select="ar" /></ar>
                </auto>
            </xsl:for-each>
        </autok_sorrend_xml>
    </xsl:template>
</xsl:stylesheet>