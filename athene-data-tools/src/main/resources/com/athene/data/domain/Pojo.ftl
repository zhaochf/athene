${pojo.getPackageDeclaration()}

<#assign classbody>
<#include "PojoTypeDeclaration.ftl"/> {

<#include "PojoFields.ftl"/>
<#--include "PojoConstructors.ftl"/-->

<#include "PojoPropertyAccessors.ftl"/>

<#include "PojoExtraClassCode.ftl"/>

}
</#assign>


import com.athene.data.domain.AbstractEntity;

${classbody}