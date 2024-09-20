// ������ ���� ������� trim(��/��)
String.prototype.trim = function() {
  var TRIM_PATTERN = /(^\s*)|(\s*$)/g;
  return this.replace(TRIM_PATTERN, "");
};

// E-Mail �˻�
function isValidEmail(email) {
var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
  if (email.search(format) != -1)
      return true; //�ùٸ� ���� ����
  return false;
}

// �ѱ� ���͸�
function isValidKorean(data){
   // UTF-8 �ڵ� �� AC00���� D7A3 ������ �˻�
var format = /^[\uac00-\ud7a3]*$/g;
  if (data.search(format) != -1)
      return true; //�ùٸ� ���� ����
  return false;
}

// ��¥ �˻�
function isValidDate(year, month, day) {
var days = new Array (31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

   if(year%4==0 && year%100 !=0 || year%400==0)
     days[1]=29;
   else
     days[1]=28;

   if(month < 1 || month > 12)
     return false;

   if(day > days[month-1] || day < 1)
     return false;
   return true;
}