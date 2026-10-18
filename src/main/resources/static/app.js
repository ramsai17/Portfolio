// Small progressive enhancement: mark the active section in the top navigation.
const navLinks = [...document.querySelectorAll('.topbar nav a')];
const sections = [...document.querySelectorAll('main section[id], .hero[id]')];
if ('IntersectionObserver' in window) {
  const observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        navLinks.forEach(link => link.classList.toggle('active', link.getAttribute('href') === `#${entry.target.id}`));
      }
    });
  }, { rootMargin: '-20% 0px -65% 0px' });
  sections.forEach(section => observer.observe(section));
}
