<?xml version="1.0" encoding="UTF-8"?>

<!--  ================================================
      gastronofo.xsl
      Joaquin Bravo Montero
      (c)Programacion en Castellano
         http://www.programacion.net
      ================================================ -->

<xsl:stylesheet
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
     xmlns:fo="http://www.w3.org/1999/XSL/Format"
		 version="1.0">

<xsl:template match="bill">

	<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

  <fo:layout-master-set>
  
    <fo:simple-page-master master-name="simple"
                  page-height="29.7cm" 
                  page-width="21cm"
                  margin-top="1cm" 
                  margin-bottom="2cm" 
                  margin-left="2.5cm" 
                  margin-right="2.5cm">
      <fo:region-body margin-top="3cm"/>
      <fo:region-before extent="3cm"/>
      <fo:region-after extent="1.5cm"/>
    </fo:simple-page-master>
  </fo:layout-master-set>
  <fo:page-sequence master-reference="simple">
  
    <fo:flow flow-name="xsl-region-body">
     <xsl:apply-templates/>
    </fo:flow>
  </fo:page-sequence>
</fo:root>	
</xsl:template>

<xsl:template match="title">

	<fo:block font-size="20pt" 
            font-family="sans-serif" 
            line-height="24pt"
            space-after.optimum="15pt"
            text-align="center"
            padding-top="3pt">
        <xsl:value-of select="."/>
  </fo:block>

</xsl:template>

<xsl:template match="table">
	<fo:block font-size="12pt" 
            font-family="sans-serif" 
            line-height="15pt"
            space-after.optimum="3pt"
            text-align="justify">
      Mesa <xsl:value-of select="."/> 
  </fo:block>
</xsl:template>

<xsl:template match="items">
	<fo:block space-after.optimum="3pt"
            space-before.optimum="4pt">	
		
		<fo:table table-layout="fixed" width="100%">
		  <fo:table-column column-width="25%"/>
		  <fo:table-column column-width="25%"/>
		  <fo:table-column column-width="25%"/>
		  <fo:table-column column-width="25%"/>		  
		  <fo:table-body>
			  <fo:table-row> 
				<fo:table-cell>
					<xsl:call-template name="name"/>
				</fo:table-cell>	
				<fo:table-cell>
					<xsl:call-template name="price"/>
				</fo:table-cell>	
				<fo:table-cell>
					<xsl:call-template name="quantity"/>
				</fo:table-cell>	
				<fo:table-cell>
					<xsl:call-template name="totalItemPrice"/>
				</fo:table-cell>					
			  </fo:table-row>
		  </fo:table-body>
		</fo:table>
	</fo:block>
</xsl:template>
		<xsl:template name="name">
			<fo:block font-size="12pt" 
					font-weight="bold"
					font-family="sans-serif" 
					line-height="13pt"
					space-after.optimum="2pt"
					text-align="justify">
			   <xsl:value-of select="name"/>
		  </fo:block>
		</xsl:template>

		<xsl:template name="price">
		   <fo:block font-size="9pt">
			 <fo:basic-link color="#0060A0" external-destination="{.}">
			   <xsl:value-of select="price"/>
			 </fo:basic-link>
		   </fo:block>	
		</xsl:template>

		<xsl:template name="quantity">
			<fo:block font-size="9pt"
					start-indent="3pt"		 
					font-family="sans-serif" 
					line-height="12pt"
					space-after.optimum="3pt"
					text-align="justify">
				 <xsl:value-of select="quantity"/>
		  </fo:block>
		</xsl:template>
		<xsl:template name="totalItemPrice"> 
			<fo:block font-size="9pt"
					start-indent="3pt"		 
					font-family="sans-serif" 
					line-height="12pt"
					space-after.optimum="3pt"
					text-align="justify">
				 <xsl:value-of select="totalItemPrice"/>
		  </fo:block>
		</xsl:template>
<xsl:template match="precioTotalMesa">
	<fo:block space-after.optimum="3pt"
            space-before.optimum="4pt">	
		
		<fo:table table-layout="fixed" width="100%">
			<fo:table-column column-width="25%"/>	
			<fo:table-column column-width="25%"/>	
			<fo:table-column column-width="25%"/>	
			<fo:table-column column-width="25%"/>			  
		  <fo:table-body>
			  <fo:table-row> 
				<fo:table-cell>
					<xsl:call-template name="vacio"/>
				</fo:table-cell>
				<fo:table-cell>
					<xsl:call-template name="vacio"/>
				</fo:table-cell>
				<fo:table-cell>
					<xsl:call-template name="vacio"/>
				</fo:table-cell>
				<fo:table-cell>
					<xsl:call-template name="precioTotalMesa"/>
				</fo:table-cell>					
			  </fo:table-row>
		  </fo:table-body>
		</fo:table>
	</fo:block>
</xsl:template>
		<xsl:template name="vacio"> 
			<fo:block font-size="9pt"
					start-indent="3pt"		 
					font-family="sans-serif" 
					line-height="12pt"
					space-after.optimum="3pt"
					text-align="justify">
				 
		  </fo:block>
		</xsl:template>
		<xsl:template name="precioTotalMesa"> 
			<fo:block font-size="9pt"
					start-indent="3pt"		 
					font-family="sans-serif" 
					line-height="12pt"
					space-after.optimum="3pt"
					text-align="justify">
				 <xsl:value-of select="."/>
		  </fo:block>
		</xsl:template>
</xsl:stylesheet>