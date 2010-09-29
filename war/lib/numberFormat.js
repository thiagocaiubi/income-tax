(function($) {
	$.fn.numberFormat = function(options) {
		var defaults = {
			maxLength: null
		};

		var options = $.extend(defaults, options);

		return this.each(function() {
				// pre defined options
				var obj = $(this);
				
				var isNumber = /[0-9]/;

				var maxLength = options.maxLength;

				function toNumbers() {
					var str = obj.val();
					var formatted = '';
					for ( var i = 0; i < (str.length); i++) {
						char = str.charAt(i);
						if (formatted.length == 0 && char == 0)
							char = false;
						if (char && char.match(isNumber)) {
							if (maxLength) {
								if (formatted.length < maxLength)
									formatted = formatted + char;
							} else {
								formatted = formatted + char;
							}
						}
					}
					return formatted;
				}

				// filter what user type (only numbers and functional keys)
				function keyCheck(e) {

					var code = (e.keyCode ? e.keyCode : e.which);
					var typed = String.fromCharCode(code);
					var functional = false;
					var str = obj.val();
					var newValue = toNumbers(str + typed);

					// allow keypad numbers, 0 to 9
					if (code >= 96 && code <= 105)
						functional = true;

					// check Backspace, Tab, Enter, and left/right arrows
					if (code == 8)
						functional = true;
					if (code == 9)
						functional = true;
					if (code == 13)
						functional = true;
					if (code == 37)
						functional = true;
					if (code == 39)
						functional = true;

					if (!functional) {
						e.preventDefault();
						e.stopPropagation();
						if (str != newValue)
							obj.val(newValue);
					}

				}

				// bind the actions
				$(this).bind('keydown', keyCheck);
				$(this).bind('keyup', toNumbers);
				if ($(this).val().length > 0)
					toNumbers();
			});
	};
})(jQuery);