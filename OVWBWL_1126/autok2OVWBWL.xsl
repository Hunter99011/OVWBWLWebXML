<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text" />
    <xsl:template match="/">
        <xsl:value-of select="count(autok/auto[ar &gt; 30000])" />
    </xsl:template>
</xsl:stylesheet>