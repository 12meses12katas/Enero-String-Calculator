<?php

class Validator {

    public function validateInput($numbers) {
        $this->searchNegativeNumbers($numbers);
        return $this->numbersBiggerThanThousandShouldBeIgnored($numbers);
    }

    private function searchNegativeNumbers($numbers) {
        $negative_numbers = array_filter($numbers, function ($value) { return $value < 0; });
        if (count($negative_numbers))
            throw new InvalidArgumentException("negatives not allowed: " . implode($negative_numbers));
    }

    private function numbersBiggerThanThousandShouldBeIgnored($numbers) {
        return array_filter($numbers, function ($value) { return $value < 1000; });
    }

}

?>
