function autocompleteStart()
{
	$('#city-autocomplete').autocomplete({
	    serviceUrl: '/city-autocomplete.json',
	    paramName: 'q',
	    minChars: 3,
	    maxHeight: 150,
	    deferRequestBy: 200,
	    onSelect: function (suggestion) {
	    	$("#cityId").val(suggestion.data);
	    }
	});
}
$(autocompleteStart);