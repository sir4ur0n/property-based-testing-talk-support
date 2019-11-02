namespace Samples.Tests
{
    using FsCheck.Xunit;
    using System.Collections.Generic;
    using Xunit;

    public class IdempotenceTests
    {
        [Fact]
        public void EmptyEmpty()
        {
            Assert.Equal(new List<string>().RemoveSmallWords(), new List<string>());
        }

        [Property(Verbose = true)]
        public void Idempotence(List<string> anyStrings)
        {
            var result1 = anyStrings.RemoveSmallWords();
            var result2 = result1.RemoveSmallWords();

            Assert.Equal(result1, result2);
        }

        [Fact]
        public void KeepLongEnough()
        {
            Assert.Equal(
                new List<string>
                    {
                        "def",
                        "ghij"
                    },
                new List<string>
                    {
                        "a",
                        "bc",
                        "def",
                        "ghij"
                    }.RemoveSmallWords());
        }

        [Fact]
        public void ManyRandom()
        {
            Assert.Equal(
                new List<string>
                    {
                        "hello",
                        "wo  rld"
                    },
                new List<string>
                    {
                        "hello",
                        "",
                        "wo  rld  ",
                        "    !!"
                    }.RemoveSmallWords());
        }

        [Fact]
        public void RemoveAllSmall()
        {
            Assert.Equal(
                new List<string>(),
                new List<string>
                    {
                        "    a",
                        "b",
                        "c            ",
                        " de "
                    }.RemoveSmallWords());
        }
    }
}
