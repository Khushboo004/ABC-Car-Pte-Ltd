function status(){
if(th:text="${c.status}" == true){
   document.getElementById("#customSwitches").disabled = false;

}else{
   document.getElementById("#customSwitches").disabled = true;
}}