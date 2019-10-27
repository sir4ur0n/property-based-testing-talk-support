namespace Samples.Tests
{
    using System.Collections.Generic;

    using FsCheck.Xunit;

    using Xunit;

    public class IdempotenceTests
    {
        [Fact]
        public void empty_empty()
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
        public void keep_long_enough()
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
        public void many_random()
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
        public void remove_all_small()
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
