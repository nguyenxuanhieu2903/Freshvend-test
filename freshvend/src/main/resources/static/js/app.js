// Mở/đóng menu bên trái trên điện thoại (trang quản lý)
(function () {
  var btn = document.querySelector('[data-menu-toggle]');
  var side = document.getElementById('sidebar');
  if (!btn || !side) return;
  function setOpen(open) {
    side.classList.toggle('is-open', open);
    btn.setAttribute('aria-expanded', String(open));
  }
  btn.addEventListener('click', function () { setOpen(!side.classList.contains('is-open')); });
  document.addEventListener('keydown', function (e) { if (e.key === 'Escape') setOpen(false); });
  document.addEventListener('click', function (e) {
    if (!side.contains(e.target) && !btn.contains(e.target)) setOpen(false);
  });
})();
