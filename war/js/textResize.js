(function($) {
	$.fn.textResize = function(options) {
		var defaults = {
			increase: null,
			decrease: null,
			text: []
		};
		
		var options = $.extend(defaults, options);
		
		return this.each(function() {
			var obj = $(this),
				resizers = {
					"increaseText": 1.1,
					"decreaseText": 0.9
				}; 
			
			$.each(options.text, function(index, text) {
				$.each(resizers, function(resizer, factor) {
					$(document).bind(resizer, {text: text}, function(event){
						
						$(event.data.text).css({
							"font-size": function(index, value) {
					        	return parseFloat(value) * factor;
					      	}, 
					      	"color": function(index, value) {
					        	return parseFloat(value) * factor;
					      	}
						});
					});
				});
			});
			
			$(options.increase).bind("click", function(event) {
				event.preventDefault();
				$(document).trigger("increaseText");
			}).css({cursor: "pointer"});
			
			$(options.decrease).bind("click", function(event) {
				event.preventDefault();
				$(document).trigger("decreaseText");
			}).css({cursor: "pointer"});
		});
	}
})(jQuery);