/**
 * Spinner jQuery Plugin
 * author thiago {dot} caiubi {at} gmail {dot} com
 */
(function(){
	$.fn.spinner = function(settings){
		settings = $.extend({
			up: undefined,
			down: undefined,
			min: undefined,
			max: undefined,
			step: 1
		}, settings || {});
		
		return this.each(function(){
			var element = $(this).addClass('spinner').attr('autocomplete', 'off');
			var allowedKeys = {
				9:0, //tab
				13:0, //enter
				37:0, //left
				38:step, //up
				39:0, //right
				40:-1*step //down
			};
			var min = 1*settings.min;
			var max = 1*settings.max;
			var up = $(settings.up);
			var down = $(settings.down);
			var step = settings.step;
			
			handleControl(up, step, 'spinner-up');
			handleControl(down, -1*step, 'spinner-down');
			
			function spin(e){
				var spinner = getSpinner(e);
				if(spinner){
					updateSpinner(spinner);
				}
			}
			
			function getSpinner(e){
				var code = e.keyCode ? e.keyCode : e.which;
				var spinner = allowedKeys[code];
				if(spinner === undefined){
					e.preventDefault();
					e.stopPropagation();
					return null;
				}
				return spinner;
			}
			
			function updateSpinner(spinner){
				var newValue = 1*element.val() + 1*spinner;
				newValue = getValueInRange(newValue, min, max);
				element.val(newValue);
			}
			
			function getValueInRange(val, min, max){
				val = !isNaN(min) && val < min ? min : val;
				val = !isNaN(max) && val > max ? max : val;
				return val;
			}
			
			function handleControl(element, step, klass){
				if(element){
					element.addClass(klass).click(function(e){
						e.preventDefault();
						e.stopPropagation();
						updateSpinner(step);
					});
				}
			}
			
			element.bind('keydown', spin);
			
		});
	};
})(jQuery);