<?php
$n = $_POST['name'];
sleep(3);
if (empty($n)) {
  header('HTTP/1.1 500 Internal Server Error');
} else {
  print('こんにちは、'.$n.'さん！');
}

