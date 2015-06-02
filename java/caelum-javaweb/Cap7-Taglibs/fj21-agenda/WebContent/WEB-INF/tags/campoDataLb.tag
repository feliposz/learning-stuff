<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="id" required="true"%>
<%@ attribute name="rotulo" required="true"%>
<label for="${id}" class="col-sm-2 control-label">${rotulo}</label>
<div class="col-sm-10">
  <input type="text" id="${id}" name="${id}" />
  <script>
			$("#{id}").datePicker({
				dateFormat : "dd/mm/yy"
			});
		</script>
</div>
