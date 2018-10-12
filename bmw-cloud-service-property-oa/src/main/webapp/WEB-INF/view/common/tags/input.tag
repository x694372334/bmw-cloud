@/*
            表单中input框标签中各个参数的说明:
    hidden : input hidden框的id
    id : input框id
    name : input框名称
    readonly : readonly属性
    clickFun : 点击事件的方法名
    style : 附加的css属性
    tiptitle : 增加tip提示   创建人：wangliqing 创建时间：20180627
    disabled : 增加失效属性    创建人：zhangchengjun 创建时间:20180610
    checked  : 增加勾选属性   创建人：zhangchengjun 创建时间:20180713
@*/
<div class="form-group">
        @if(isNotEmpty(required)){
        	<label class="col-sm-3 control-label">${name}<span style="color:red;">*</span></label>
        @}
        @if(!isNotEmpty(required)){
       		 <label class="col-sm-3 control-label">${name}</label>
        @}
    
     @if(isNotEmpty(tiptitle)){
        <span class="tooltip-show" data-toggle="tooltip" title="${tiptitle}"><font color="red">?</font></span>
     @}
    <div class="col-sm-9">
        <input class="form-control" id="${id}" name="${id}"
               @if(isNotEmpty(value)){
                    value="${tool.dateType(value)}"
               @}
               @if(isNotEmpty(type)){
                    type="${type}"
               @}else{
                    type="text"
               @}
               @if(isNotEmpty(readonly)){
                    readonly="${readonly}"
               @}
               @if(isNotEmpty(hidden)){
                    hidden="${hidden}"
               @}
               @if(isNotEmpty(clickFun)){
                    onclick="${clickFun}"
               @}
               @if(isNotEmpty(style)){
                    style="${style}"
               @}
               @if(isNotEmpty(disabled)){
                    disabled="${disabled}"
               @}
               @if(isNotEmpty(checked)){
                    checked="${checked}"
               @}
        >           
        @if(isNotEmpty(hidden)){
            <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}">
        @}
        @if(isNotEmpty(selectFlag)){
            <div id="${selectId}" style="display: none; position: absolute; z-index: 200;">
                <ul id="${selectTreeId}" class="ztree tree-box" style="${selectStyle!}"></ul>
            </div>
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


