<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" />
    <xsl:template match="/">
        <rendszamok>
            <xsl:for-each select="autok/auto">
                <rendszam>
                    <xsl:value-of select="@rsz" />
                </rendszam>
            </xsl:for-each>
        </rendszamok>
    </xsl:template>
</xsl:stylesheet>