watch( 'string_calculator_spec.rb' )  {|md| system("spec #{md[0]}") }
watch( 'string_calculator.rb' )      {|md| system("spec string_calculator_spec.rb") }
