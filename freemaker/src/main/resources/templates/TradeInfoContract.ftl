<div class="card-first">
	<div class="crad-second">
		<table>
			<#list list as note>
			<tr>
				<td class="left-item">Symbol</td>
				<td class="right-item"><![CDATA[${note.symbol}]]></td>
				<td class="left-item"></td>
				<td class="right-item"></td>
			</tr>
			<tr>
				<td class="left-item">Principal Transaction</td>
				<td class="right-item"><![CDATA[${note.principalTransaction}]]></td>
				<td class="left-item">Brokerage &amp; GST</td>
				<td class="right-item">${note.brokerageGst?string(",##0.00")}</td>
			</tr>
			<tr>
				<td class="left-item">Strike Price</td>
				<td class="right-item">${note.strikePrice?string(",##0.00")}</td>
				<td class="left-item">Expiry Date</td>
				<td class="right-item"><![CDATA[${note.expiryDate}]]></td>
			</tr>
			</#list>
		</table>
	</div>
</div>