<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<meta charset="ISO-8859-1" />
				<title>Contatos</title>
			</head>
			<body>
			<h2 align="center">Contatos</h2>
			<hr/>
				<table border="1" align="center">
					<tr>
						<th rowspan="2">Nome</th>
						<th colspan="3">Telefone</th>
						<th rowspan="2">Departamento</th>
						<th rowspan="2">Empresa</th>

					</tr>
					<tr>
						<th>DDD</th>
						<th>Numero</th>
						<th>Ramal</th>
					</tr>
					<xsl:apply-templates />
				</table>
			</body>
		</html>
		<!-- TODO: Auto-generated template -->
	</xsl:template>

	<xsl:template match="pessoa">
		<tr>
			<td>
				<xsl:value-of select="nome" />
			</td>
			<td>
				<xsl:value-of select="telefone/ddd" />
			</td>
			<td>
				<xsl:value-of select="telefone/numero" />
			</td>
			<td>
				<xsl:value-of select="telefone/ramal" />
			</td>
			<td>
				<xsl:value-of select="departamento" />
			</td>
			<td>
				<xsl:value-of select="empresa" />
			</td>

		</tr>
	</xsl:template>
</xsl:stylesheet>