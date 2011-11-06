assert = require("assert");

//taken from http://simonwillison.net/2006/Jan/20/escape/
RegExp.escape = function(text) {
  return text.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
}

StringCalculator = (function () {
  var parseString = function (s) {
    var tmp = s;
    var scan;
    var delimiters = /,|\n/

    scan = s.match(/^\/\/(\[.+\])+\n/);
    //checking if there's a delimiter definition
    if (scan) {
      scan = scan[1];
      delimiters = []
      pattern = /\[([^\[\]]+)\]/g
      while(tmp = (pattern).exec()){
        delimiters.push(RegExp.escape(tmp[1]));
      }
      delimiters = delimiters.join("|")
      delimiters = new RegExp(delimiters);
      tmp = s.split("\n"); tmp.shift();
      tmp = tmp.join();
    }
    return tmp.split(delimiters);
  };

  var negatives;

  var checkNum = function (n) {
    if (n < 0) {
      negatives.push(n);
      return false;
    }
    if (n > 1000) {
      return false;
    }
    return true;
  }

  return {
    add: function (numbers) {
      var nums;
      var sum = 0;
      if (numbers === "") {
        return 0;
      }

      negatives = [];

      nums = parseString(numbers);
      for (i = 0; i < nums.length; i++) {
        if (checkNum(nums[i])) {
          sum = sum + parseInt(nums[i]);
        }
      }

      if (negatives.length > 0) {
        throw new Error("Tried to add negatives: " + negatives.join(","));
      }

      return sum;
    }
  };
})();

assert.equal(StringCalculator.add(""),0);
assert.equal(StringCalculator.add("1,2,3"),6);
assert.equal(StringCalculator.add("//[me]\n1me2"),3);
assert.equal(StringCalculator.add("//[*]\n1*2"),3);
assert.equal(StringCalculator.add("//[*][hola]\n1*2hola1001"),3);
try {
  StringCalculator.add("//[me]\n-1me-2");
  console.log("Something went wrong, this message shouldn't be printed");
}
catch (Error) {}
