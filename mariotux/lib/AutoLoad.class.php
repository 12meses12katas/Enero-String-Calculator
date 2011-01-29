<?php

/*
 * http://www.pensandoenred.com
 * Copyright (C) 2011 Mario Nunes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses/.
 */

class AutoLoad {

    private static $_obj;

    public function __construct() {
        spl_autoload_register(array('AutoLoad', 'autoload'));
    }

    public function autoload($class_name) {
        $cmd = 'find ' . __DIR__ . '/../src -type f -name "' . $class_name . '.class.php"';

        $filename = realpath(exec($cmd, $output));
        if (file_exists($filename))
            require_once $filename;
        else
            throw new Exception('Class not found: ' . $class_name . '');
    }

    public static function register() {
        if (!is_object(self::$_obj)) {
            self::$_obj = new AutoLoad();
        }
    }

}

?>