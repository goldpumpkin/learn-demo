<h1 style="margin-top:9px;margin-bottom:8px;">
	Confirmation <#if status=='REVERSED' || status=='CANCELLED'>Reversed</#if>
</h1>
<div>Date of Issuance: ${dateOfIssuance}</div>
<div class="card-first">
	<div class="descriptions" style="display: block;">
		<div class="descriptions-item" style="display: inline-block; width: 33%">
			<p style="font-size: 14px;font-weight:bold">${accountName}</p>
			<span class="value">
				<nobr>
					<#if address1??>${address1} &#160;</#if>
					<#if address2??>${address2} &#160;</#if>
					<#if address3??>${address3} &#160;</#if>
					<#if address4??>${address4}</#if>
				</nobr>
				<br/>
				<#if city??>${city} &#160;</#if>
				<#if state??>${state} &#160;</#if>
				<#if country??>${country} &#160;</#if>
				<#if postalCode??>${postalCode}</#if>
			</span>
		</div>
		<#if jointAccountName??>
		<div class="descriptions-item" style="display: inline-block; width: 33%">
			<p style="font-size: 14px;font-weight:bold">${jointAccountName}</p>
			<span class="value">
				<nobr>
					<#if jointAddress1??>${jointAddress1} &#160;</#if>
					<#if jointAddress2??>${jointAddress2} &#160;</#if>
					<#if jointAddress3??>${jointAddress3} &#160;</#if>
					<#if jointAddress4??>${jointAddress4}</#if>
				</nobr>
				<br/>
				<#if jointCity??>${jointCity} &#160;</#if>
				<#if jointState??>${jointState} &#160;</#if>
				<#if jointCountry??>${jointCountry} &#160;</#if>
				<#if jointPostalCode??>${jointPostalCode}</#if>
			</span>
		</div>
		</#if>
		<div class="descriptions-item" style="display: inline-block; width: 33%">
			<div>
				<span class="label">Account No&#160;:&#160;</span>
				<span class="value">${accountNumber}</span>
			</div>
            <div>
                <span class="label">Account Type&#160;:&#160;</span>
                <span class="value">
                    <![CDATA[${accountType}]]>
                </span>
            </div>
		</div>
	</div>
</div>